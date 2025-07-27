public class ErrorLogProcessor extends LogProcessor{
    InfoLogProcessor(LogProcessor nexLogProcessor){
        super(nexLogProcessor);
    }

    public void log(int logLevel, String message){

        if (logLevel == ERROR) {
            System.out.println("INFO : " + message);
        } else {
            super.log(logLevel, message);
        }
    }
}
