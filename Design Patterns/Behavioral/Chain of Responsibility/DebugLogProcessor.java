public public class DebugLogProcessor extends LogProcessor{
    InfoLogProcessor(LogProcessor nexLogProcessor){
        super(nexLogProcessor);
    }

    public void log(int logLevel, String message){

        if (logLevel == DEBUG) {
            System.out.println("INFO : " + message);
        } else {
            super.log(logLevel, message);
        }
    }
}