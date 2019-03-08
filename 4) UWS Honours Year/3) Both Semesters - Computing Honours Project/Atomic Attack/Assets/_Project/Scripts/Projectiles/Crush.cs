using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Crush : MonoBehaviour {

    [Space(-10)]
#pragma warning disable
    [SerializeField] string Effect = "Crush everyone.";

    void OnTriggerEnter2D(Collider2D collision)
    {
        HealthSystem HP = collision.gameObject.GetComponent<HealthSystem>();
        if (collision.gameObject.name == "Castle Health")
        { HP.DamageTaken(500); }
        else if (collision.gameObject.tag == "Enemy" || collision.gameObject.tag == "Friend")
        { HP.DamageTaken(150); }
    }
}