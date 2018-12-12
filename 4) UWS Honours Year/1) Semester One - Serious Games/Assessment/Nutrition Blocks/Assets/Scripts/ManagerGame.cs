using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class ManagerGame : MonoBehaviour {

    public bool BallLaunched;
    public float BallSpeed;
    public float BallSpeedCurrent;
    public bool PlayerIsPlaying;
    public int PlayerLives;
    public float PlayerWeight;
    public float PlayerSpeed;
    public float PlayerFallVelocity;
    public Text UIScore;
    public float ScoreCurrent;
    public Image UIHelp;
    int Limit = 6000;
    public enum DifficultyState { Beginner, Easy, Medium, Hard };
    public DifficultyState Difficulty = DifficultyState.Beginner;
    public Text UIDeadType;
    public Text UIDeadName;
    public Text UIDeadPoints;
    [SerializeField] float TimeScale;

    void Start()
    {
        BallLaunched = false;
        PlayerIsPlaying = true;
        ScoreCurrent = 0;
    }

    //if (Input.GetKey(KeyCode.R)) { SceneManager.LoadScene("Game"); }
    //if (Input.GetKey(KeyCode.Escape)) { Application.Quit(); }
    // To pause, just make TimeScale == 0 and PlayerMoving == false.
    // Make new scene for start menu.
	
	void Update() 
    {
        // Fading the UIHelp image out in accordance to ScoreCurrent.    
        Color temp = UIHelp.GetComponent<Image>().color;    // Getting alpha of UIHelp image.
        temp.a = (Limit - ScoreCurrent) / Limit;            // Turning the alpha into a percentage.
        UIHelp.GetComponent<Image>().color = temp;          // Rewriting original alpha.

        // As PlayerWeight drops, Speed slows down.
        PlayerWeight -= Time.deltaTime * 20; // x4 PlayerWeight drops to '0' every 25 seconds.

        if (BallLaunched == true && PlayerLives != 0)
        {
            ScoreCurrent += Time.deltaTime;   // "n0" == to zero decimal places.
            UIScore.text = ScoreCurrent.ToString("n0");
        }
        // Score stops when Player is respawning.
        if (PlayerIsPlaying == false) { ScoreCurrent -= Time.deltaTime; }

        // Setting Difficulty of Game based on Current Score.
        if (ScoreCurrent >=  600 && ScoreCurrent <= 1999) { Difficulty = DifficultyState.Easy; }
        if (ScoreCurrent >= 2000 && ScoreCurrent <= 3999) { Difficulty = DifficultyState.Medium; }
        if (ScoreCurrent >= 4000)                         { Difficulty = DifficultyState.Hard; }
    }
}