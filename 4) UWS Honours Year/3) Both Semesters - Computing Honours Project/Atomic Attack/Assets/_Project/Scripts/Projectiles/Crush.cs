using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Crush : MonoBehaviour {

    [Space(-10)]
#pragma warning disable
    [SerializeField] string Effect = "Crush everyone.";
    int Damage = 500;

    void OnTriggerEnter2D(Collider2D collision)
    {
        HealthSystem HP = collision.gameObject.GetComponent<HealthSystem>();
        ManagerGame ManagerGame = GameObject.Find("Manager Game").GetComponent<ManagerGame>();
        if (collision.gameObject.name == "Castle Health")
        {
            HP.DamageTaken(Damage);
            ManagerGame.CurrentScore += Damage * 100000;
            ManagerGame.CurrentGold  += Damage;
        }
        else if (collision.gameObject.tag == "Enemy" || collision.gameObject.tag == "Friend")
        { HP.DamageTaken(150); }
    }
}