using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class F17_Chlorine : MonoBehaviour {

    [Space(-10), Header("[ Poison everyone ]")]
    #pragma warning disable
    [SerializeField] string Duration = "5 seconds.";

    void OnTriggerStay2D(Collider2D collision)
    {
        HealthSystem HP = collision.gameObject.GetComponent<HealthSystem>();
        if (collision.gameObject.name == "Castle Health")
        { /* Nothing. */}
        else if (collision.gameObject.tag == "Enemy")
        { HP.DamageTaken(1); } // Not running away in Enemies because already got Poison.
        else if (collision.gameObject.tag == "Friend") // Gas-Masks to appear in only Friendlies.
        { collision.gameObject.GetComponent<AI_Human>().RunAway = true; }
    }

    void Start() { StartCoroutine(Timer(2)); }
    IEnumerator Timer(int Time) { yield return new WaitForSeconds(Time); gameObject.SetActive(false); }
}