CREATE TABLE employee (
    [id] UNIQUEIDENTIFIER NOT NULL,
    [name] NVARCHAR(50) NOT NULL,
    [birthday] DATE NOT NULL,
    [created_at] DATETIME2 DEFAULT GETDATE() NOT NULL,
    [leave_at] DATETIME2,

    PRIMARY KEY([id])
);

CREATE TABLE time_clock_record (
    [id] INT NOT NULL,
    [type] VARCHAR(3) NOT NULL, -- 'in' for punch-in or 'out' for punch-out
    [employee_id] UNIQUEIDENTIFIER NOT NULL,
    [created_at] DATETIME2 DEFAULT GETDATE() NOT NULL,

    PRIMARY KEY([id]),
    FOREIGN KEY([employee_id]) REFERENCES employee
);

CREATE TABLE base_form (
    [id] UNIQUEIDENTIFIER NOT NULL,
    [type] TINYINT NOT NULL, -- 1: Leave form, 2: Quit form
    [created_by] UNIQUEIDENTIFIER NOT NULL,
    [approved_by] UNIQUEIDENTIFIER NOT NULL,
    [agent_id] UNIQUEIDENTIFIER NOT NULL,
    [note] NVARCHAR(300),
    [created_at] DATETIME2 DEFAULT GETDATE() NOT NULL,
    [last_modified_at] DATETIME2 DEFAULT GETDATE() NOT NULL,

    PRIMARY KEY([id]),
    FOREIGN KEY [created_by] REFERENCES employee([id]),
    FOREIGN KEY [approved_by] REFERENCES employee([id]),
    FOREIGN KEY [agent_id] REFERENCES employee([id])
);

CREATE TABLE leave_form (
    [id] UNIQUEIDENTIFIER NOT NULL,
    [type] TINYINT NOT NULL, -- 1: Special leave, 2: Sick leave, 3: Official leave, 4: Personal leave
    [reason] NVARCHAR(1200),
    [from] SMALLDATETIME NOT NULL, 
    [to] SMALLDATETIME NOT NULL, 

    PRIMARY KEY([id]),
    FOREIGN KEY([id]) REFERENCES base_form([id])
);

CREATE TABLE quit_form (
    [id] UNIQUEIDENTIFIER NOT NULL,
    [reason] NVARCHAR(1200) NOT NULL,
    [estimated_quit_date] SMALLDATETIME NOT NULL,

    PRIMARY KEY([id]),
    FOREIGN KEY([id]) REFERENCES base_form([id])
);