package techtown.org.blescanner.HTTP_REST.Util;

import android.os.SystemClock;
import android.view.View;

import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

public class Utility {

    public Time time = new Time();

    public Time getTime(){
        return time;
    }

    public class Time{
        long systemTime;
        String gmtCode;
        String requestDateTime;

        public long getSystemTime() {
            return systemTime;
        }

        public String getGmtCode() {
            return gmtCode;
        }

        public String getRequestDateTime() {
            return requestDateTime;
        }

        public Time() {
            systemTime = System.currentTimeMillis();
            Date date = new Date(systemTime);
            date.getTime();
            //min api 24 ==> 26으로 올림
            ZoneOffset offset  = ZonedDateTime.now().getOffset();
            gmtCode = "GMT"+String.valueOf(offset);
            gmtCode = gmtCode.replace(":","");

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            requestDateTime = simpleDateFormat.format(date);
        }
    }

    public abstract static class OnSingleClickListener implements View.OnClickListener {
        /**
         * 最短click事件的时间间隔
         */
        private static final long MIN_CLICK_INTERVAL=3000;
        /**
         * 上次click的时间
         */
        private long mLastClickTime;

        /**
         * click响应函数
         * @param v The view that was clicked.
         */
        public abstract void onSingleClick(View v);

        @Override
        public final void onClick(View v) {
            long currentClickTime=SystemClock.uptimeMillis();
            long elapsedTime=currentClickTime-mLastClickTime;
            //有可能2次连击，也有可能3连击，保证mLastClickTime记录的总是上次click的时间
            mLastClickTime=currentClickTime;

            if(elapsedTime<=MIN_CLICK_INTERVAL)
                return;

            onSingleClick(v);
        }

    }



}
