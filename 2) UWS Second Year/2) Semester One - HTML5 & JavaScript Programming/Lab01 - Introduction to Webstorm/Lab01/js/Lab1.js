/**
 * Created with JetBrains WebStorm.
 * User: alistair
 * Date: 06/09/2012
 * Time: 20:01
 * To change this template use File | Settings | File Templates.
 */

/**
 * Here's our daysOld() function.
 * Note that Math.floor() is a function that truncates everything after the decimal point
 * from a number, so you end up with an integer.
 * @param dob : The actual date of birth as a date object.
 * @returns {number}: The whole number of days old the person whose birthday has been passed in is.
 */
function daysOld(dob){
	var msPerDay = 1000 * 60 * 60 * 24,
		now = new Date(),
		diff = now - dob;
		return Math.floor(diff / msPerDay);
}

/**
 * And the equivalent yearsOld() one...
 * @param dob: The date of birth.
 * @returns {number}: The whole number of years old this birthday represents.
 */
function yearsOld(dob){
    var msPerYear = 1000 * 60 * 60 * 24 * 365.25,
        now = new Date(),
        diff = now - dob;
    return Math.floor(diff / msPerYear);
}

/**
 * window.onload is a placeholder for a function that will be called once all of the HTML in a
 * web page has been loaded into the browser. That's a very useful event to have access to, since
 * it is the first place where you can guarantee that any bit of the page you want to alter,
 * update or otherwise manipulate will be in memory now.
 */
window.onload = function(){
    /**
     * This little bit of code (commented out) demonstrates how to schedule a function to
     * be executed periodically (every 5000mS, or 5 seconds in this case).
     * First, create the function you want to be called.  Then call setInterval() passing
     * the time between invocations and the name of the function.

    var f = function(){
      alert("Time's up");
    };
    setInterval(f, 5000);

    */

    /**
     * Here we are accessing a reference to an HTML element, and stashing it in a variable
     * (para) for future use.  The second line assigns new contents to that element.
     * Note that if you're using Firefox browser, this code wont work becasue (for some weird
     * unknown reason) the Firefox <p> element does not have an innerText property.  All the other
     * browsers have it, so Firefox is playing silly buggers here.
     * A cross browser work-around would be to code it as...
     *
     *     if(para.innerText !== undefined){
     *        para.innerText = "Some text";
     *     } else {
     *        para.textContent = "Some text";
     *     }
     *
     * If you were writing a strategically important web app, you would be best to do this.
     */
    var para = document.getElementById("heading");
    para.innerText = "A short exercise on creating dynamic web content.  Note this text has been added by script.";

    /**
     * This is another use for a reference ot an HTML element.
     * In this case, I'm attaching a function definition to an *event* that we know the
     * element handles.  Now, whenever the user clicks on the button, the function will
     * be executed...
     */
    var button = document.getElementById("button");
    button.onclick = function(){
        alert("I've been clicked");
    };

    /**
     * This one does the same for whe someone selects a new <option> from the list.
     * Note how we pick up the text that the user selected from the list...
     *     list.selectedIndex - gives you the number (counting from 0) of the list item chosen.
     *     list.options[]     - is an array of all of the option elements in the list.
     *     list.options[list.selectedIndex]  - indicates the selected <option> element.
     *     list.options[list.selectedIndex].value  - provides the text of that <option>
     */
    var list = document.getElementById("list");
    list.onchange = function(){
        var colour = list.options[list.selectedIndex].value;
        changeColour(item);
    };

    /**
     * In this one, we're reacting to the event of the user selecting a new date.
     * Note that this will vary from browser to browser, since they don't all yet
     * provide the nice calendar control for selecting dates.
     * Note that for any <input> control, there are a number of different events you
     * could use.  oninput is fired every time a new value is established, onblur is
     * fired when the cursor moves off this control, onchange is fired when any part
     * of the value changes etc.
     */
    var dob = document.getElementById("dob");
    // Note - in the line below I've replaced dob.oninput with dob.onblur
    // The .onblur event happens when the cursor moves away from a control, which means the user
    // gets a proper chance to update the whole date instead of just a bit of it.
    dob.onblur = function(){
        alert("Your birth date is: " + dob.value);
        alert("You are "+daysOld(dob.valueAsDate)+" days old.");
        alert("You are "+yearsOld(dob.valueAsDate)+" years old.");
    };

    /**
     * These two elements are needed to make our "slider with feedback" work.
     * A reference to the <input> range control is needed so we can catch events
     * when the value is changed.  The <span> value is needed to have somewhere to
     * display the updated value of the range.
     */
    var range = document.getElementById("range");
    var value = document.getElementById("value");
    range.onchange = function(){
        value.innerText = range.value;
    };
};

function changeColour(colour){
    /**  This original version of the function body shows how to use a switch() statement.
     *   That's fair enough, but a far more sensible approach is just to recognise that
     *   the browser knows the name of a wide range of colours - see below the commented bit.
    var c = 0;
    switch(colour){
        case "Red":
            c = "#f00";
            break;
        case "Green":
            c = "#0f0";
            break;
        case "Blue":
            c = "#00f";
            break;
    }
    document.bgColor = c;
    */

    /**
     * This one is much better, since it can deal with any valid colour name.
     * HTML colour names can be found at http://en.wikipedia.org/wiki/Web_colors
     */
    document.bgColor = colour;  // We're assuming colour contains a valid name.
};



