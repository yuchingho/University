using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class F16_Sulphur : MonoBehaviour {

    [Space(-10), Header("[ Mist: F16_Sulphur ] Explosions ++")]
    #pragma warning disable
    [SerializeField] string Effect = "Also Run Away.";

    void OnTriggerStay2D(Collider2D collision)
    {
        GameObject[] UpgradeDetonation = GameObject.FindGameObjectsWithTag("Explode");
        foreach (GameObject Upgrade in UpgradeDetonation)
        { if (Upgrade != null) { Upgrade.GetComponent<_Explode>().Magnified = true; } }

        if (collision.gameObject.name == "Castle Health")
        { /* Nothing. */}
        else if (collision.gameObject.tag == "Enemy" || collision.gameObject.tag == "Friend")
        { collision.gameObject.GetComponent<AI_Human>().RunAway = true; }
    }
}