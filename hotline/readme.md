# ReadMe

## `Word Table to Excel Line` config file template

### YAML Style

```yaml
wordDirectory: "D:\\_test\\word_files_ready_to_read"
wordTableIndices: "1"
wordTableCellLocation: "[2,2][2,6][3,6][4,2][6,2][7,2][1,4][10,2]"
excelFilepath: "D:\\_test\\excel_file_for_record\\Mayor_Hotline.xlsx"
excelInsertLineNumber: "2"
excelInsertSheetIndex: "1"
wordFilesMoveTo: "D:\\_test\\word_files_counted"

```

## How to Launch the Program by Command Line

### project structure

```text
└─ app.jar
└─ lib
    └─ javafx
        └─ javafx-base-17.0.1-win.jar
        └─ javafx-controls-17.0.1-win.jar
        └─ javafx-fxml-17.0.1-win.jar
        └─ javafx-graphics-17.0.1-win.jar
    └─ paperwork.jar
    └─ poi-5.2.3.jar
    ... omit ...
```

### command

```bash
java --module-path .\lib\javafx --add-modules javafx.controls,javafx.graphics,javafx.fxml -jar app.jar
```

### executable way

```c
#include <stdlib.h>
#include <Windows.h>

int main()
{
    ShowWindow(GetConsoleWindow(), SW_HIDE);
    system("java --module-path .\\lib\\javafx --add-modules javafx.controls,javafx.graphics,javafx.fxml -jar app.jar");
    return 0;
}
```