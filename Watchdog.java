package com.qualcomm.ftcrobotcontroller.us.newberg.bullet;
import com.qualcomm.robotcore.hardware.DcMotorController;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Bullet Proof on 12/10/2015.
 */
public class WatchDog extends Thread{
        final private BulletProofMode _target;
        final private AtomicBoolean _running;
        final private AtomicBoolean _stopped;
        final private AtomicLong _millis;

        public WatchDog(BulletProofMode target, long millis)
        {
            super();

            _target = target;
            _millis = new AtomicLong(millis);
            _running = new AtomicBoolean(true);
            _stopped = new AtomicBoolean(false);
        }

        /**
         * Stops the timer without stopping the motors
         */
        synchronized public void Terminate()
        {
            _stopped.set(true);
        }

        /**
         * Set the timer delay
         *
         * @param millis Delay in milliseconds
         */
        synchronized public void SetDelay(long millis) { _millis.set(millis); }
        synchronized public boolean GetRunning() { return _running.get(); }

        @Override
        public void run()
        {
            if (_target == null)
                return;

            if (_millis.get() <= 0)
                return;

            _running.set(true);

            try
            {
                sleep(_millis.get());
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            if (_stopped.get())
                return;

            _running.set(false);
        }
}
