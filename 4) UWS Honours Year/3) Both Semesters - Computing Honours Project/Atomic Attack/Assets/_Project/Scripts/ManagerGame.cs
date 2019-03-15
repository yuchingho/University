using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class ManagerGame : MonoBehaviour {

    [SerializeField] GameObject HelpUI;
    public Button Pause;
    bool Paused;
    [SerializeField] GameObject LoseUI;


    public int Seconds; public int Minutes;
    public float TimePlayed;
    public float CurrentScore;
    public int CurrentGold;
    public int CurrentLives = 50;
    public int CurrentE_Gunmen;
    public int CurrentE_Swordsmen;
    public int TotalFriends;
    public int TotalEnemies;
    [HideInInspector] public int CopyE_Gunmen;
    [HideInInspector] public int CopyE_Swordsmen;

    [SerializeField] Text UITimer;
    [SerializeField] Text UIScore;
    [SerializeField] Text UIGold;
    [SerializeField] Text UILives;
    [SerializeField] GameObject LifeLost;

    void Awake()
    {
        Time.timeScale = 0;
        HelpUI.SetActive(true);
        CurrentScore -= Time.deltaTime;
    }

    void Update() 
    {
        if (CurrentLives < 0)
        {
            Time.timeScale = 0;
            LoseUI.SetActive(true);
        }
        // Time.
        Minutes = Mathf.FloorToInt(TimePlayed / 60);
        Seconds = Mathf.FloorToInt(TimePlayed % 60);
        TimePlayed += Time.deltaTime;
        UITimer.text = Minutes.ToString("00") + ":" + Seconds.ToString("00");

        // Score.
        CurrentScore += Time.deltaTime;
        UIScore.text = CurrentScore.ToString("n0");

        // Gold.
        UIGold.text  = CurrentGold. ToString("n0");
        UILives.text = CurrentLives.ToString();

        TotalEnemies = CurrentE_Gunmen + CurrentE_Swordsmen;
    }

    public void ButtonRestart()
    {
        SceneManager.LoadScene("Game");
        // Usually need below, but resetting it in void Awake().
        //Time.timeScale = 1;
    }

    public void ButtonQuit()
    {
        Application.Quit();
    }

    public void ButtonPausePressed()
    {
        if (Paused == false)
        {
            Time.timeScale = 0;
            Paused = true;
            HelpUI.SetActive(true);
        }
        else if (Paused == true)
        {
            Time.timeScale = 1;
            Paused = false;
            HelpUI.SetActive(false);
        }
    }

    void OnTriggerEnter2D(Collider2D collision)
    {
        if (collision.gameObject.tag == "Enemy")
        {
            Destroy(collision.gameObject);
            StartCoroutine(LifeLostFlash(0.05f));
            CurrentLives--;
        }
    }

    IEnumerator LifeLostFlash(float Time)
    {
        LifeLost.gameObject.SetActive(true);
        yield return new WaitForSeconds(Time);
        LifeLost.gameObject.SetActive(false);
    }
}