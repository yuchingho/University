using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class ManagerGame : MonoBehaviour {

    public int ScoreCurrent;
    public float TimePlayed;
    public int Gold;
    public int Lives = 50;
    public int E_Gunmen;
    public int E_Swordsmen;
    bool Paused = false;

    [SerializeField] Text UIScore;
    [SerializeField] Text UITimer;
    [SerializeField] Text UIGold;
    [SerializeField] Text UILives;

    void Start()
    {
        
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