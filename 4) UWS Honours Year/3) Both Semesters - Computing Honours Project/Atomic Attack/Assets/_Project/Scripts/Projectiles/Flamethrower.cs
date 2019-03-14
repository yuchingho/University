using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Flamethrower : MonoBehaviour {

    [Space(-10)]
    #pragma warning disable
    [SerializeField] string Effect = "Burns everyone.";
    int Damage = 1;

    void OnTriggerStay2D(Collider2D collision)
    {
        HealthSystem HP = collision.gameObject.GetComponent<HealthSystem>();
        ManagerGame ManagerGame = GameObject.Find("Manager Game").GetComponent<ManagerGame>();
        if (collision.gameObject.name == "Castle Health")
        {   // Not setting "Burn" active since Castle doesn't have it.
            HP.DamageTaken(Damage);
            ManagerGame.CurrentScore += Damage * 100000;
            ManagerGame.CurrentGold  += Damage;
        }
        else if (collision.gameObject.tag == "Enemy" || collision.gameObject.tag == "Friend")
        { HP.DamageTaken(0.41f); collision.gameObject.GetComponent<AI_Human>().Burned = true;
            if (collision.gameObject.GetComponent<AI_Human>().MovementSpeed != 0)
            {   collision.gameObject.GetComponent<AI_Human>().MovementSpeed = 2f; } } } }