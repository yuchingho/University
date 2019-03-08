using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Flamethrower : MonoBehaviour {

    [Space(-10)]
    #pragma warning disable
    [SerializeField] string Effect = "Burns everyone.";

    void OnTriggerStay2D(Collider2D collision)
    {
        HealthSystem HP = collision.gameObject.GetComponent<HealthSystem>();
        if (collision.gameObject.name == "Castle Health")
        { HP.DamageTaken(1.0f); /* Not setting "Burn" active since Castle doesn't have it. */}
        else if (collision.gameObject.tag == "Enemy" || collision.gameObject.tag == "Friend")
        { HP.DamageTaken(0.4f); collision.gameObject.GetComponent<AI_Human>().Burned = true; }
    }
}