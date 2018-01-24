using UnityEngine;
using System.Collections;

public class Bounds : MonoBehaviour {

    const int maxDistance = 100;
    public bool IsMainObject = false;

    void OnTriggerEnter2D(Collider2D collision)
    {
        if (collision.CompareTag("Bounds"))
        {
            DestroyAndRemoveFromActive();
        }
    }

    void DestroyAndRemoveFromActive()
    {
        if (IsMainObject)
        {
            bool hasParachute = false;
            bool parentHasParachute = false;
            ParachuteContainer parachuteContanier = GetComponent<ParachuteContainer>();
            if (parachuteContanier)
            {
                hasParachute = parachuteContanier.HasParachute;
            }
            ParachuteContainer parachuteContanierInParent = null;
            if (transform.parent)
            {
                parachuteContanierInParent = transform.parent.GetComponent<ParachuteContainer>();
            }
            if (parachuteContanierInParent)
            {
                parentHasParachute = parachuteContanierInParent.HasParachute;
            }
            if (hasParachute || parentHasParachute)
            {
                return;
            }

            Game.RemoveEnemyOrProp(tag);
        }
        Destroy(gameObject);
    }

	void Update () 
    {
        if (Vector3.Distance(Game.PlayerBody.transform.position, transform.position) > maxDistance)
        {
            DestroyAndRemoveFromActive();
        }
	}
}
