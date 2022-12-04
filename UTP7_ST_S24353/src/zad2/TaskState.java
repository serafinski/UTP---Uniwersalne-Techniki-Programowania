package zad2;

public enum TaskState {
    //CREATED — zadanie utworzone, ale nie zaczęło się jeszcze wykonywać,
    CREATED,
    //RUNNING — zadanie się wykonuje w odrębnym wątku
    RUNNING,
    //ABORTED — wykonanie zadania zostało przerwane
    ABORTED,
    //READY — zadanie zakończyło się pomyślnie i są gotowe wyniki
    READY
}
