using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class ManagerGame : MonoBehaviour {

    public float TimePlayed;
    public float ScoreCurrent;
    public int Lives = 200;
    public int CounterEnemiesKilled;
    public int CounterFriendsKilled; // Collateral Damage
    bool Paused = false;

    [SerializeField] Text UITimer;
    [SerializeField] Text UIGold;
    [SerializeField] Text UILives;

    void Start()
    {
        // Counter for the Game Data, and will be passed onto for local High Score.
        // How to make sure "tutorial-like" pop-up shows up only once?
        DontDestroyOnLoad(this);
	}
	
	void Update() 
    {
        if (Input.GetKeyDown(KeyCode.P) && Paused == false) { Time.timeScale = 0; Paused = true;  }
        else if (Input.GetKeyDown(KeyCode.P) && Paused == true ) { Time.timeScale = 1; Paused = false; }
        if (Input.GetKeyDown(KeyCode.Escape)) { Application.Quit(); }

        UILives.text = Lives.ToString();

    }

    void OnTriggerEnter2D(Collider2D collision)
    {
        if (collision.gameObject.tag == "Enemy")
        {
            Destroy(collision.gameObject);
            Lives--;
        }
    }
}