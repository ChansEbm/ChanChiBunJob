package com.app.recall.entity;

/**
 * Created by KenChan on 17/1/9.
 */

public class Configuration {

    public static class Notification {
        boolean sound = false;
        boolean vibrate = false;

        public Notification(boolean sound, boolean vibrate) {
            this.sound = sound;
            this.vibrate = vibrate;
        }

        public void setSound(boolean sound) {
            this.sound = sound;
        }

        public void setVibrate(boolean vibrate) {
            this.vibrate = vibrate;
        }

        public boolean isSound() {
            return sound;
        }

        public boolean isVibrate() {
            return vibrate;
        }
    }
}
