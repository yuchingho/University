using UnityEngine;
using UnityEngine.SceneManagement;

public class EnemyHealth : MonoBehaviour
{
    public int MaxHealth;
    public int CurrentHealth;

    public string scene;

    // Use this for initialization
    void Start()
    {
        CurrentHealth = MaxHealth;
    }

    // Update is called once per frame
    void Update()
    {
        if (CurrentHealth <= 0)
        {
            // gameObject.SetActive(false);
            Destroy(gameObject);
            ChangeScene();
        }
    }

    public void EnemyHurt(int Damage)
    {
        CurrentHealth -= Damage;
    }

    public void SetMaxHealth()
    {
        CurrentHealth = MaxHealth;
    }

    public void ChangeScene()
    {
        SceneManager.LoadScene(scene);
    }
}