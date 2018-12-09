using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class ManagerGame : MonoBehaviour {

    public float BallSpeed;
    public float BallSpeedCurrent;  // How to set Ball Speed Limit so ball doesn't slow down to unplayable?
    public bool BallLaunched;
    public float PlayerSpeed;
    public float PlayerSpeedCurrent;
    public bool PlayerIsPlaying;
    public int PlayerLives;
    public Text ScoreUI;
    public float ScoreCurrent;
    public GameObject UIHelp;
    int Limit = 5000;
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

        if (BallLaunched == true && PlayerLives != 0)
        {
            ScoreCurrent += Time.deltaTime; // n0 == to 0dp.
            ScoreUI.text = ScoreCurrent.ToString("n0");
        }
        // Score stops when Player is respawning.
        if (PlayerIsPlaying == false) { ScoreCurrent -= Time.deltaTime;  }

        if (ScoreCurrent <= Limit)
        {   // Fading the UIHelp image out in accordance to ScoreCurrent.
            Color temp = UIHelp.GetComponent<SpriteRenderer>().color;   // Getting alpha of UIHelp image.
            temp.a = ((Limit - ScoreCurrent) / Limit) * 2;              // Turning the alpha into a percentage.
            UIHelp.GetComponent<SpriteRenderer>().color = temp;         // Rewriting original alpha.
        }

        // Setting Difficulty of Game based on Current Score.
        if (ScoreCurrent >=  600 && ScoreCurrent <= 2000) { Difficulty = DifficultyState.Easy; }
        if (ScoreCurrent >= 2000 && ScoreCurrent <= 4000) { Difficulty = DifficultyState.Medium; }
        if (ScoreCurrent >= 4000)                         { Difficulty = DifficultyState.Hard; }
    }
}