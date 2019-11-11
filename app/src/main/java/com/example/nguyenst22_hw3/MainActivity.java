/**
 * Represents a 15 Square puzzle/game
 * All controls, listeners, etc.
 * are located here
 * @author: Stephen Nguyen
 * @version: 11/8/19
 */
package com.example.nguyenst22_hw3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    /**
     External Citation
     Date: 2 November 2019
     Problem: Did not know how to use a GridLayout
     Resource:
     https://developer.android.com/reference/android/widget/GridLayout
     Solution: I used the documentation.
     */

    //Declaring objects that are used in multiple
    //methods (variable scope prevents them from
    // being used outside onCreate)
    androidx.gridlayout.widget.GridLayout board;
    Button blank;
    TextView message;
    Button[] buttonList = new Button[16];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creating objects that are intended to be used
        //Some TextViews are not intended to be changed
        //so no listener/declaration is needed

        //Typecasting for setting findView removed, as IDE says it
        //is redundant
        board = findViewById(R.id.board);

        blank = findViewById(R.id.blank);
        message = findViewById(R.id.message);

        Button one = findViewById(R.id.one);
        Button two = findViewById(R.id.two);
        Button three = findViewById(R.id.three);
        Button four = findViewById(R.id.four);
        Button five = findViewById(R.id.five);
        Button six = findViewById(R.id.six);
        Button seven = findViewById(R.id.seven);
        Button eight = findViewById(R.id.eight);
        Button nine = findViewById(R.id.nine);
        Button ten = findViewById(R.id.ten);
        Button eleven = findViewById(R.id.eleven);
        Button twelve = findViewById(R.id.twelve);
        Button thirteen = findViewById(R.id.thirteen);
        Button fourteen = findViewById(R.id.fourteen);
        Button fifteen = findViewById(R.id.fifteen);
        Button resetButton = findViewById(R.id.reset);

        //Creating listeners for appropriate objects
        //Some objects do not need a listener
        //such as some of the TextViews
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        ten.setOnClickListener(this);
        eleven.setOnClickListener(this);
        twelve.setOnClickListener(this);
        thirteen.setOnClickListener(this);
        fourteen.setOnClickListener(this);
        fifteen.setOnClickListener(this);
        resetButton.setOnClickListener(this);

        //Set buttonList such that buttons
        //are placed in order
        buttonList[0] = one;
        buttonList[1] = two;
        buttonList[2] = three;
        buttonList[3] = four;
        buttonList[4] = five;
        buttonList[5] = six;
        buttonList[6] = seven;
        buttonList[7] = eight;
        buttonList[8] = nine;
        buttonList[9] = ten;
        buttonList[10] = eleven;
        buttonList[11] = twelve;
        buttonList[12] = thirteen;
        buttonList[13] = fourteen;
        buttonList[14] = fifteen;
        buttonList[15] = blank;

        //Initially set the board to a random layout
        randomize(board);

        //Set "win" text to blank, as user hasn't won yet
        //(unless randomize somehow sets them properly)
        message.setText("");

        //Set background color of each button according
        //to its position
        checkIndices(board, buttonList);
    }

    /**
     * Performs an action when something is pressed
     * Action depends on what is clicked
     * @param v: View to act on
     */
    public void onClick(View v)
    {
        //Need to check which button is being pressed
        //then execute appropriate action
        if (v.getId() == R.id.reset)
        {
            randomize(board);
        }

        //Although there are other objects
        //such as the TextViews
        //no listener has been made for them
        //so they can be ignored
        else
        {
            move(board, v);
        }
    }

    /**
     * Moves a button to the empty spot
     * Only works if the empty spot is adjacent to it
     * @param gl: GridLayout to act on
     * @param b: Button to move
     */
    public void move(androidx.gridlayout.widget.GridLayout gl, View b)
    {
        //Get index of b and blank, used for checking adjacency
        //of empty spot, assume they are at indices 0 and 1
        int index = 0;
        int blankIndex = 1;

        //Iterate through GridLayout and get actual indices
        for (int i = 0; i < gl.getChildCount(); i++)
        {
            if (gl.getChildAt(i) == b)
            {
                index = i;
            }

            if (gl.getChildAt(i) == blank)
            {
                blankIndex = i;
            }
        }

        //To prevent out of bounds errors when checking
        //if empty spot is adjacent, several checks are
        //needed

        //These checks first check the position of the index
        //of the button that is not blank, then check if the
        //blank button index is adjacent to it, then swaps them
        //if so. Adjacency depends on the position of the button.

        //Top left corner
        if (index == 0)
        {
            if (blankIndex == index + 1 || blankIndex == index + 4)
            {
                swapButtons(gl, gl.getChildAt(index),gl.getChildAt(blankIndex));
            }
        }

        //Top right corner
        else if (index == 3)
        {
            if (blankIndex == index - 1 || blankIndex == index + 4)
            {
                swapButtons(gl, gl.getChildAt(index),gl.getChildAt(blankIndex));
            }
        }

        //Bottom left corner
        else if (index == 12)
        {
            if (blankIndex == index + 1 || blankIndex == index - 4)
            {
                swapButtons(gl, gl.getChildAt(index),gl.getChildAt(blankIndex));
            }
        }

        //Bottom right corner
        else if (index == 15)
        {
            if (blankIndex == index - 1 || blankIndex == index - 4)
            {
                swapButtons(gl, gl.getChildAt(index),gl.getChildAt(blankIndex));
            }
        }

        //Left edge
        else if (index == 4 || index == 8)
        {
            if (blankIndex == index - 4 ||
                    blankIndex == index + 1 ||
                    blankIndex == index + 4)
            {
                swapButtons(gl, gl.getChildAt(index),gl.getChildAt(blankIndex));
            }
        }

        //Top edge
        else if (index == 1 || index == 2)
        {
            if (blankIndex == index - 1 ||
                    blankIndex == index + 1 ||
                    blankIndex == index + 4)
            {
                swapButtons(gl, gl.getChildAt(index),gl.getChildAt(blankIndex));
            }
        }

        //Right edge
        else if (index == 7 || index == 11)
        {
            if (blankIndex == index - 4 ||
                    blankIndex == index + 4 ||
                    blankIndex == index - 1)
            {
                swapButtons(gl, gl.getChildAt(index),gl.getChildAt(blankIndex));
            }
        }

        //Bottom edge
        else if (index == 13 || index == 14)
        {
            if (blankIndex == index - 1 ||
                    blankIndex == index - 4 ||
                    blankIndex == index + 1)
            {
                swapButtons(gl, gl.getChildAt(index),gl.getChildAt(blankIndex));
            }
        }

        //Elsewhere
        else
        {
            if (blankIndex == index - 1 ||
                    blankIndex == index + 1 ||
                    blankIndex == index + 4 ||
                    blankIndex == index - 4)
            {
                swapButtons(gl, gl.getChildAt(index),gl.getChildAt(blankIndex));
            }
        }

        //Check button placement
        checkIndices(gl, buttonList);
    }

    /**
     * Randomizes layout of buttons in a GridLayout
     * @param gl: GridLayout to randomize
     */
    public void randomize(androidx.gridlayout.widget.GridLayout gl)
    {
        Random rng = new Random();
        /**
         External Citation
         Date: 3 November 2019
         Problem: Did not know how iterate through a GridLayout
         Resource:
         https://stackoverflow.com/questions/19523860/iterate-through-all-objects-in-gridview
         Solution: I used the example code/solution.
         */

        for (int i = 0; i < gl.getChildCount(); i++)
        {
            /**
             External Citation
             Date: 3 November 2019
             Problem: Could not think of how to randomize children of GL
             Resource:
             Samuel Nguyen
             Solution: I used his assistance for the algorithm described below
             */

            //Randomizing can be thought of as
            //swapping children between index i and a random one

            //Index to swap with
            int indexToSwap = rng.nextInt(gl.getChildCount() - 1);

            //Take whatever is at indexToSwap and put it at i
            //then put temp into indexToSwap after deleting
            //current view
            swapButtons(gl, gl.getChildAt(i),gl.getChildAt(indexToSwap));
        }

        //Update background color of buttons
        checkIndices(gl, buttonList);
    }

    /**
     * Swaps the position of two buttons
     * Used as a helper method
     * This method currently does not work successfully
     * when called from move
     * @param gl: GridLayout to act on
     * @param view1: First View to swap
     * @param view2: Second View to swap
     */
    public void swapButtons(androidx.gridlayout.widget.GridLayout gl, View view1, View view2)
    {
        /**
         External Citation
         Date: 5 November 2019
         Problem: My original algorithm generated an error that said
         that the view already had a parent and removeView() had to be
         called on its parent
         Resource:
         https://stackoverflow.com/questions/28071349/
         the-specified-child-already-has-a-parent-you-must-call-removeview-on-the-chil
         Solution: I used the solution provided
         */

        if (view1.getParent() != null)
        {
            ((ViewGroup) view1.getParent()).removeView(view1);
        }

        gl.addView(view1, gl.indexOfChild(view2));

        if (view2.getParent() != null)
        {
            ((ViewGroup) view2.getParent()).removeView(view2);
        }

        gl.addView(view2, gl.indexOfChild(view1));

    }

    /**
     * Checks if all buttons in a GridLayout are in the right spot
     * Set background color of each button
     * to red if in wrong spot, green if in right spot
     * If all buttons are correctly placed, update message
     * to show that the game is won
     * @param gl: GridLayout to act on
     * @param list: List of buttons to use for comparison
     */
    public void checkIndices(androidx.gridlayout.widget.GridLayout gl, Button[] list)
    {
        //Represents how many buttons are in their proper place
        int cellsDone = 0;

        //Iterate through GridLayout
        //and see what buttons are in the
        //right spot
        for (int i = 0; i < gl.getChildCount(); i++)
        {
            //Assume that each button is in the wrong spot
            gl.getChildAt(i).setBackgroundColor(Color.RED);

            //If in the correct spot, change color and number of correct
            //buttons by one
            if (gl.getChildAt(i) == list[i])
            {
                gl.getChildAt(i).setBackgroundColor(Color.GREEN);
                cellsDone++;
            }
        }

        //The game is won if all buttons are correctly placed.
        if (cellsDone == gl.getChildCount())
        {
            message.setText("Solved.");
        }
    }
}
