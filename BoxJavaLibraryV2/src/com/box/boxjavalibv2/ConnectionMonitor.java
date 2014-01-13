package com.box.boxjavalibv2;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.HttpParams;

public class ConnectionMonitor {

    private static ClientConnectionManager connManager;

    // TODO: this need to be changed for real production code, either use a real singleton pattern or other way to get parameters of ClienConnectionManager set.
    public static ClientConnectionManager getConnectionManagerInstance(final HttpParams params, final SchemeRegistry schemeReg,
        final long timePeriodCleanUpIdleConnection, final long idleTimeThreshold) {
        if (connManager == null) {
            connManager = new ThreadSafeClientConnManager(params, schemeReg);
            ConnectionMonitor.monitorConnection(connManager, timePeriodCleanUpIdleConnection, idleTimeThreshold);
        }

        return connManager;
    }

    /**
     * 
     * @param connManager
     * @param timePeriodCleanUpIdleConnection
     *            clean up idle connection every such period of time.
     * @param idleTimeThreshold
     *            time threshold, an idle connection will be closed if idled above this threshold of time.
     */
    private static void monitorConnection(ClientConnectionManager connManager, final long timePeriodCleanUpIdleConnection, final long idleTimeThreshold) {
        final WeakReference<ClientConnectionManager> ref = new WeakReference<ClientConnectionManager>(connManager);
        connManager = null;
        Thread monitorThread = new Thread() {

            @Override
            public void run() {
                try {
                    while (true) {
                        synchronized (this) {
                            ClientConnectionManager connMan = ref.get();
                            if (connMan == null) {
                                return;
                            }

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
