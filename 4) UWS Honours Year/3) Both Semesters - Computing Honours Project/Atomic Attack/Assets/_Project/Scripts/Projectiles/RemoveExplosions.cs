using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class RemoveExplosions : MonoBehaviour {

    // https://answers.unity.com/questions/52005/destroy-game-object-after-animation.html
    public void DestroyExplosions()
    {
        Destroy(gameObject);
    }
}