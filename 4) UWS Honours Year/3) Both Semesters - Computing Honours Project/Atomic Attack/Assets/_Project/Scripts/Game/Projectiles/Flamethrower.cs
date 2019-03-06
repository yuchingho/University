using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Flamethrower : MonoBehaviour {

    void OnTriggerStay2D(Collider2D collision)
    {   // Not setting "Burn" active since Castle doesn't have it.
        if (collision.gameObject.name == "Castle Health")
        {   collision.gameObject.GetComponent<HealthSystem>().DamageTaken(1f); }

        else if (collision.gameObject.tag == "Enemy" || collision.gameObject.tag == "Friend")
        {
            collision.gameObject.GetComponent<AI_Human>().Burned = true;
            collision.gameObject.GetComponent<HealthSystem>().DamageTaken(0.2f);
        }
    }
}