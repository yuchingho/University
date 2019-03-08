using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Poison_Fl : MonoBehaviour {

    [Space(-10), Header("[ Poison everyone + Gas Masks]")]
    #pragma warning disable
    [SerializeField] string Duration = "Till Dead.";

    void OnTriggerStay2D(Collider2D collision)
    {
        HealthSystem HP = collision.gameObject.GetComponent<HealthSystem>();
        if (collision.gameObject.name == "Castle Health")
        { /* Nothing. */}
        else if (collision.gameObject.tag == "Enemy")
        { HP.DamageTaken(0.5f); } // Not running away in Enemies because already got Poison.
        else if (collision.gameObject.tag == "Friend") // Gas-Masks to appear in only Friendlies.
        { collision.gameObject.GetComponent<AI_Human>().RunAway = true; }
    }
}