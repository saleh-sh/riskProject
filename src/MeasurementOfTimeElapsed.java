public class MeasurementOfTimeElapsed extends Thread {

    private BoardView boardView;

    public MeasurementOfTimeElapsed(BoardView boardView) {
        this.boardView = boardView;
    }

    private int millis = 0;
    private int second = 0;
    private int minutes = 0;
    private int hour = 0;

    public int getSecond() {
        return second;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getHour() {
        return hour;
    }

    @Override
    public void run() {
        for (; ; ) {

            try {
                sleep(1);
                if (millis > 1000) {
                    millis = 0;
                    second++;
                }
                if (second > 60) {
                    millis = 0;
                    second = 0;
                    minutes++;
                }
                if (minutes > 60) {
                    millis = 0;
                    second = 0;
                    minutes = 0;
                    hour++;
                }

                millis++;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            boardView.getHoursLabel().setText(hour + " : ");
            boardView.getMinutesLabel().setText(minutes + " : ");
            boardView.getSecondsLabel().setText(second + "");
        }
    }
}
