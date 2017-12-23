using UnityEngine;
using System.Collections;

public class JointManager : MonoBehaviour {

    public bool IsPlayer = true;

    void OnCollisionEnter2D(Collision2D collision)
    {
        if (collision.gameObject.CompareTag("Parachute") || collision.gameObject.CompareTag("Bounds"))
        {
            return;
        }

        if ((IsPlayer && collision.gameObject.tag != "Player") || (!IsPlayer && collision.transform.parent != transform.parent))
        {
            Destroy(GetComponent<Joint2D>());
            ParachuteContainer parachuteContainer = transform.GetComponentInParent<ParachuteContainer>();
            if (parachuteContainer)
            {
                parachuteContainer.UnEquipParachute();
            }
            tag = "Untagged";
            transform.parent = null;
        }
    }
}
