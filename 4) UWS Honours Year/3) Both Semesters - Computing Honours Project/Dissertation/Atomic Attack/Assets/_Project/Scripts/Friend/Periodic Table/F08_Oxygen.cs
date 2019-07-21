using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class F08_Oxygen : MonoBehaviour {

    [Space(-10), Header("[ Mist: F08_Oxygen ]")]
    #pragma warning disable
    [SerializeField] string Effect = "Magnifies explosions.";

    void OnTriggerStay2D(Collider2D collision)
    {
        GameObject[] UpgradeDetonation = GameObject.FindGameObjectsWithTag("Explode");
        foreach (GameObject Upgrade in UpgradeDetonation)
        { if (Upgrade != null) { Upgrade.GetComponent<_Explode>().Magnified = true; } }
    }
}