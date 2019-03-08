using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Poison_Cl : MonoBehaviour {

    [Space(-10), Header("[ Poison everyone ]")]
    #pragma warning disable
    [SerializeField] string Duration = "5 seconds.";

    void OnTriggerStay2D(Collider2D collision)
    {
        HealthSystem HP = collision.gameObject.GetComponent<HealthSystem>();
        if (collision.gameObject.name == "Castle Health")
        { /* Nothing. */}
        else if (collision.gameObject.tag == "Enemy" || collision.gameObject.tag == "Friend")
        { HP.DamageTaken(0.5f);}
    }
}