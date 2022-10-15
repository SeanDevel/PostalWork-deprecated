# How to Launch the Program by Command Line

## project structure

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

## command

```bash
java --module-path .\lib\javafx --add-modules javafx.controls,javafx.graphics,javafx.fxml -jar app.jar
```
