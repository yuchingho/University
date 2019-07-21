using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class CastleHealth : HealthSystem {

    ManagerGame ManagerGame;
    public Slider HealthBar;
    public Text HealthText;
    int InitialHealth;

    [SerializeField] GameObject WinUI;
    [SerializeField] Text StatsUI;

    void Start()
    {
        ManagerGame = GameObject.Find("Manager Game").GetComponent<ManagerGame>();
        InitialHealth = (int)Health;
        HealthBar.maxValue = InitialHealth;
        WinUI.SetActive(false);
	}
	
	void Update() 
    {
        HealthBar.value = Health;
        HealthText.text = Health.ToString("n0") + " / " + InitialHealth.ToString("n0");
        if (HealthBar.value <= 0)
        {
            Time.timeScale = 0;
            WinUI.SetActive(true);
            StatsUI.text =
                ": " + ManagerGame.Minutes.ToString("00") + ":" + ManagerGame.Seconds.ToString("00")
       + "\n" + ": " + ManagerGame.CurrentScore.ToString("n0")
       + "\n" + ": " + ManagerGame.CurrentLives.ToString()
       + "\n" + ": " + ManagerGame.TotalEnemies.ToString()
       + "\n" + ": " + ManagerGame.TotalFriends.ToString();
        }
    }
}