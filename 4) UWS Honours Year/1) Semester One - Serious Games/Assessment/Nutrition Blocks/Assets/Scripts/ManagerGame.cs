using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

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
    public GameObject UIHelp;
    int Limit = 4000;   // Set to Easy Score for now.
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
        //Time.timeScale = 0.5f; // Slow-motion if needed.
        if (Input.GetKey(KeyCode.R)) { SceneManager.LoadScene("Game"); }
        if (Input.GetKey(KeyCode.Escape)) { Application.Quit(); }

        if (BallLaunched == true)
        {
            //ScoreCurrent += Time.deltaTime; // n0 == to 0dp.
            ScoreUI.text = ScoreCurrent.ToString("n0");
        }

        if (ScoreCurrent <= Limit)
        {   // Fading the UIHelp image out in accordance to ScoreCurrent.
            Color temp = UIHelp.GetComponent<SpriteRenderer>().color;   // Getting alpha of UIHelp image.
            temp.a = ((Limit - ScoreCurrent) / Limit) * 2;              // Turning the alpha into a percentage.
            UIHelp.GetComponent<SpriteRenderer>().color = temp;         // Rewriting original alpha.
        }

        // Setting Difficulty of game based on Current Score.
        if (ScoreCurrent >= 1000 && ScoreCurrent <= 2000) { Difficulty = DifficultyState.Easy; }
    }
}