package ctrlcctrlv.happytraveller.animations;

import android.os.Handler;
import android.widget.Button;

/**
 * A class to make a cool animation on map`s button.
 *
 */
public class AnimatedButton
{
    Button button;

    public AnimatedButton(Button button)
    {
        this.button = button ;
    }


    public void makeButtonTextTo(final String text , int delayTime)
    {
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                button.setText(text);
            }
        }, delayTime);
    }



    public void makeButtonTextTo(final String text , final int delayTime ,final String text2 , final int delayTime2)
    {
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                button.setText(text);
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        button.setText(text2);
                    }
                }, delayTime);
            }
        }, delayTime2);

    }




    public void makeButtonTextTo(final String text , final int delayTime ,final String text2 , final int delayTime2 ,final String text3 , final int delayTime3)
    {
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                button.setText(text);
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        button.setText(text2);
                        new Handler().postDelayed(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                button.setText(text3);
                            }
                        }, delayTime);
                    }
                }, delayTime2);
            }
        }, delayTime3);

    }





    public  void makeButtonTextTo(final String text, final int delayTime, final String text2, final int delayTime2, final String text3, final int delayTime3, final String text4, final int delayTime4)
    {
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                button.setText(text);
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        button.setText(text2);
                        new Handler().postDelayed(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                button.setText(text3);
                                new Handler().postDelayed(new Runnable()
                                {
                                    @Override
                                    public void run()
                                    {
                                        button.setText(text4);
                                    }
                                }, delayTime);
                            }
                        }, delayTime2);
                    }
                }, delayTime3);
            }
        }, delayTime4);

    }

}
