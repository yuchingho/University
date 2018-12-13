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
    public bool HitByBall;
    public bool MethodDone;
    public Text UIWeight;
    public int TimeScale;
    public bool PauseActive;
    public GameObject PauseBG;

    void Start()
    {
        BallLaunched = false;
        PlayerIsPlaying = true;
        ScoreCurrent = 0;
        HitByBall = false;
        MethodDone = false;
        TimeScale = 1;
        PauseActive = false;
    }

	void Update() 
    {
        Time.timeScale = TimeScale;
        if (Input.GetKeyDown(KeyCode.Escape) && PauseActive == false)
        {
            TimeScale = 0;
            PauseActive = true;
            PlayerIsPlaying = false;
            PauseBG.SetActive(true);
        }
        else if (Input.GetKeyDown(KeyCode.Escape) && PauseActive == true)
        {
            TimeScale = 1;
            PauseActive = false;
            PlayerIsPlaying = true;
            PauseBG.SetActive(false);
        }

        if (PauseActive == true && Input.GetKey(KeyCode.R))     { SceneManager.LoadScene("Game"); }
        if (PauseActive == true && Input.GetKey(KeyCode.Space)) { SceneManager.LoadScene("Menu"); }


        // Fading the UIHelp image out in accordance to ScoreCurrent.    
        Color temp = UIHelp.GetComponent<Image>().color;    // Getting alpha of UIHelp image.
        temp.a = (Limit - ScoreCurrent) / Limit;            // Turning the alpha into a percentage.
        UIHelp.GetComponent<Image>().color = temp;          // Rewriting original alpha.

        if (BallLaunched == true)
        {
            ScoreCurrent += Time.deltaTime;   // "n0" == to zero decimal places.
            UIScore.text = ScoreCurrent.ToString("n0");

            if (PlayerIsPlaying == true)
            {
                PlayerWeight -= Time.deltaTime * 4;      // PlayerWeight drops to '0' every 25 seconds,
                PlayerSpeed = 25 * (PlayerWeight / 100); // PlayerSpeed also slows down.
                UIWeight.text = "KG:\n" + PlayerWeight.ToString("n0");
                if (PlayerWeight >= 1 && PlayerWeight <= 30)    // Will start flashing
                { UIWeight.color = Color.Lerp(Color.black, Color.red, Mathf.PingPong(Time.time, 0.25f)); }
            } 
        }

        // If PlayerWeight drops below 
        if (PlayerWeight <= 0 && MethodDone == false)
        {
            PlayerIsPlaying = false;
            HitByBall = true;
            LoseLife();
        }

        // Score stops when Player is respawning.
        if (PlayerIsPlaying == false) { ScoreCurrent -= Time.deltaTime; }

        // Setting Difficulty of Game based on Current Score.
        if (ScoreCurrent >=  600 && ScoreCurrent <= 1999) { Difficulty = DifficultyState.Easy; }
        if (ScoreCurrent >= 2000 && ScoreCurrent <= 3999) { Difficulty = DifficultyState.Medium; }
        if (ScoreCurrent >= 4000)                         { Difficulty = DifficultyState.Hard; }
    }

    // Taking away a PlayerLife by passing through method once.
    // It works, but messy code with randoom boooleans.
    // Make better next time.
    void LoseLife() { PlayerLives--; MethodDone = true; }
}