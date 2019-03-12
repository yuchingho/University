using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class ManagerGame : MonoBehaviour {

    [SerializeField] int Seconds;
    [SerializeField] int Minutes;
    public float TimePlayed;
    public float CurrentScore;
    public int CurrentGold;
    public int CurrentLives = 50;
    public int CurrentE_Gunmen;
    public int CurrentE_Swordsmen;
    public int TotalFriends;
    public int TotalEnemies;
    bool Paused;

    [SerializeField] Text UITimer;
    [SerializeField] Text UIScore;
    [SerializeField] Text UIGold;
    [SerializeField] Text UILives;

    void Update() 
    {
        if (Input.GetKeyDown(KeyCode.P) && Paused == false) { Time.timeScale = 0; Paused = true;  }
        else if (Input.GetKeyDown(KeyCode.P) && Paused == true ) { Time.timeScale = 1; Paused = false; }
        if (Input.GetKeyDown(KeyCode.Escape)) { Application.Quit(); }

        Minutes = Mathf.FloorToInt(TimePlayed / 60);
        Seconds = Mathf.FloorToInt(TimePlayed % 60);
        TimePlayed += Time.deltaTime;
        UITimer.text = Minutes.ToString("00") + ":" + Seconds.ToString("00");

        CurrentScore += Time.deltaTime * 10000;
        UIScore.text = CurrentScore.ToString("n0");

        UIGold.text  = CurrentGold. ToString("n0");
        UILives.text = CurrentLives.ToString();

        //TotalFriends += TotalFriends;
        TotalEnemies = CurrentE_Gunmen + CurrentE_Swordsmen;
    }

    void OnTriggerEnter2D(Collider2D collision)
    {
        if (collision.gameObject.tag == "Enemy")
        {
            Destroy(collision.gameObject);
            CurrentLives--;
        }
    }
}