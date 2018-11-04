using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class Game : MonoBehaviour {

    Ball BallLaunched;  // Getting Ball.cs
    public float ScoreCurrent;
    public Text ScoreUI;

	void Start()
    {
        BallLaunched = GameObject.Find("Ball").GetComponent<Ball>();
        ScoreCurrent = 0;
	}
	
	void Update() 
    {
        if (BallLaunched.Launched == true)
        {
            ScoreCurrent += Time.deltaTime; // n0 == to 0dp
            ScoreUI.text = ScoreCurrent.ToString("n0");
        }
    }
}