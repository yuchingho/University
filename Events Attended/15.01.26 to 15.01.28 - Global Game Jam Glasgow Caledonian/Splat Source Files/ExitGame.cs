using UnityEngine;
using System.Collections;

public class ExitGame : MonoBehaviour {

	void Update () 
    {
	    if(Input.GetKeyDown(KeyCode.Escape))
        {
            Application.Quit();
        }
	}
}
