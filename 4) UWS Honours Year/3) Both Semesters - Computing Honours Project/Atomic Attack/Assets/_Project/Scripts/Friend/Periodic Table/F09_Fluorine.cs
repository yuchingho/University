using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class F09_Fluorine : MonoBehaviour {

    [Space(-10), Header("[ Poison everyone + Gas Masks]")]
    #pragma warning disable
    [SerializeField] string Duration = "Till Dead.";

    void OnTriggerStay2D(Collider2D collision)
    {
        HealthSystem HP = collision.gameObject.GetComponent<HealthSystem>();
        if (collision.gameObject.name == "Castle Health")
        { /* Nothing. */}
        else if (collision.gameObject.tag == "Enemy" || collision.gameObject.tag == "Friend")
        { HP.DamageTaken(1); }


    }

    void Start() { StartCoroutine(Timer(4)); }
    IEnumerator Timer(int Time) { yield return new WaitForSeconds(Time); Destroy(gameObject); }
}