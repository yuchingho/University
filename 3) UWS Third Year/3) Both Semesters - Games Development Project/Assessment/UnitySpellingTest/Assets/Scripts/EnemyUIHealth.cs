using UnityEngine;
using UnityEngine.UI;

public class EnemyUIHealth : MonoBehaviour
{
    public Slider HealthBar;
    public Text HealthText;
    public EnemyHealth EnemyHealth;

    // Use this for initialization
    void Start()
    {

    }

    // Update is called once per frame
    void Update()
    {
        HealthBar.maxValue = EnemyHealth.MaxHealth;
        HealthBar.value = EnemyHealth.CurrentHealth;
        HealthText.text = EnemyHealth.CurrentHealth + " / " + EnemyHealth.MaxHealth;
    }
}