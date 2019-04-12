using UnityEngine;
using System.Collections;

public class Parachute : MonoBehaviour {

    void OnCollisionEnter2D(Collision2D collision)
    {

        if (( collision.gameObject.tag == "Player") || (collision.gameObject.tag == "Enemy"))
        {
            ParachuteContainer paraContainer = collision.gameObject.GetComponent<ParachuteContainer>();
            if(!paraContainer)
            {
                paraContainer = collision.gameObject.GetComponentInParent<ParachuteContainer>();
            }
            if (paraContainer)
            {
                paraContainer.EquipParachute(gameObject, this);
            }
        }
    }

}
