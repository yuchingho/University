using UnityEngine;
using System.Collections;

public class HeadShotKill : MonoBehaviour {

    private int HeadShotCount = 0;
    public int HeadShotLimit = 3;
    private float HeadShotMaxTimeout = 300f;
    private float HeadShotTimeoutTimer = 0;

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () 
    {
            if (HeadShotTimeoutTimer > 0)
            {
                HeadShotTimeoutTimer -= Time.deltaTime;
            }


            if (this.HeadShotCount >= HeadShotLimit)
            {
                this.GetComponent<HingeJoint2D>().enabled = false;
                transform.parent = null;
                if (CompareTag("Player") && !Game.bIsPlayerDead)
                {
                    Game.bIsPlayerDead = true;
                }
            }
	
	}


    void OnCollisionEnter2D(Collision2D col)
    {

        if(!Game.bIsPlayerDead)
        {

            if (col.gameObject.tag != "Player")
            {
                this.HeadShotCount++;
                this.HeadShotTimeoutTimer = this.HeadShotMaxTimeout;
            }

        }

    }
}
