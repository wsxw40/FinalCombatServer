package test;

public abstract class Listener {

    public static final Listener DEFAULT_LISTENER = new Listener() {
    };

    public void onPreparedSend(Event<Request> e) {
    }

    public void onSendComplete(Event<Request> e) {
    }

    public void onReceived(Event<Request> e) {
    }

}
