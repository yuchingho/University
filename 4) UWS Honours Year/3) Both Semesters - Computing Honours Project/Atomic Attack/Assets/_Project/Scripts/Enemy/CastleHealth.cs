using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class CastleHealth : HealthSystem {

    public Slider HealthBar;
    public Text HealthText;
    int InitialHealth;





	void Start()
    {
        InitialHealth = (int)Health;
        HealthBar.maxValue = InitialHealth;
	}
	
	void Update() 
    {
        HealthBar.value = Health;
        HealthText.text = Health.ToString() + " / " + InitialHealth;
	}
}