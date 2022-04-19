public class Mass {
    public static void main(String[] args) {
        if(args.length == 0 || args[0].equals("--gui")) {
            new MainWin("Mav's Animal Shelter Software");
        } else if(args[0].equals("--cli")) {
            (new MainCli()).cli();
        } else {
            System.err.println("usage: java Mass [--gui | --cli]");
        }
    }
}
