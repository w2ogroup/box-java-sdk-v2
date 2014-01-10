package com.box.boxjavalibv2;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

import org.apache.http.conn.ClientConnectionManager;

public class ConnectionMonitor {

    /**
     * 
     * @param connManager
     * @param timePeriodCleanUpIdleConnection
     *            clean up idle connection every such period of time.
     * @param idleTimeThreshold
     *            time threshold, an idle connection will be closed if idled above this threshold of time.
     */
    public static void monitorConnection(final ClientConnectionManager connManager, final long timePeriodCleanUpIdleConnection, final long idleTimeThreshold) {
        final WeakReference<ClientConnectionManager> ref = new WeakReference<ClientConnectionManager>(connManager);
        Thread monitorThread = new Thread() {

            @Override
            public void run() {
                try {
                    ClientConnectionManager connMan = ref.get();
                    // while not garbage collected.
                    while (connMan != null) {
                        synchronized (this) {
                            wait(timePeriodCleanUpIdleConnection);
                            // Close expired connections
                            connMan.closeExpiredConnections();
                            connMan.closeIdleConnections(idleTimeThreshold, TimeUnit.SECONDS);
                        }
                    }
                }
                catch (InterruptedException ex) {
                    // terminate
                }
            }
        };
        monitorThread.start();
    }
}
