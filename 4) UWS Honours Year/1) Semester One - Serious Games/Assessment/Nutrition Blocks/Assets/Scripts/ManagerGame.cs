using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class ManagerGame : MonoBehaviour {

    public float BallSpeed;
    public float BallSpeedCurrent;
    public bool BallLaunched;
    // How to set Ball Speed Limit so ball doesn't slow down to unplayable?
    public float PlayerSpeed;
    public float PlayerSpeedCurrent;    // if PlayerIsPlaying == false && PlayerSpeedCurrent ++ 0, respawn, for lives?
    public bool PlayerIsPlaying;
    public Text ScoreUI;
    public float ScoreCurrent;
    public enum DifficultyState { Beginner, Easy, Medium, Hard };
    public DifficultyState Difficulty = DifficultyState.Beginner;

    void Start()
    {
        BallLaunched = false;
        PlayerIsPlaying = true;
        ScoreCurrent = 0;
	}
	
	void Update() 
    {
        // Time.timeScale = 0.5f; // Slow-motion if needed.
        if (BallLaunched == true)
        {
            //ScoreCurrent += Time.deltaTime; // n0 == to 0dp.
            ScoreUI.text = ScoreCurrent.ToString("n0");
        }
        // add rest of difficulties for score checkpoints
        if (ScoreCurrent >= 1000) { Difficulty = DifficultyState.Easy; }
    }
}