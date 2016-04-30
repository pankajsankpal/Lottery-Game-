package org.crce.accelerometer;

/**
 * Created by Admin on 03/03/2016.
 */
public interface AccelerometerListener {

    public void onAccelerationChanged(float x, float y, float z);

    public void onShake(float force);
}
