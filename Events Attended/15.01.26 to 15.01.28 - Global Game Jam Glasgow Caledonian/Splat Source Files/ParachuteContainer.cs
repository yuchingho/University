using UnityEngine;
using System.Collections;

public class ParachuteContainer : MonoBehaviour {
    [HideInInspector]
    public bool HasParachute = false;
    public bool IsPlayer = false;
    Parachute parachuteScript;
    GameObject parachute;
    bool isDeployed = false;

    float timeWithParachute = 0;

    void OnCollisionEnter2D(Collision2D collision)
    {
        if (collision.gameObject.CompareTag("Parachute") || collision.gameObject.CompareTag("Ground"))
        {
            return;
        }

        if ((IsPlayer && collision.gameObject.tag != "Player") || (!IsPlayer && collision.transform.parent != transform) && !isDeployed)
        {
            UnEquipParachute();
        }
    }

    public void EquipParachute(GameObject parachute, Parachute parachuteScript)
    {
        this.parachuteScript = parachuteScript;
        this.parachute = parachute;
        parachuteScript.enabled = false;
        parachute.rigidbody2D.isKinematic = true;
        parachute.collider2D.enabled = false;
        parachute.transform.parent = transform;
        parachute.transform.localPosition = Vector3.zero;
        parachute.transform.localEulerAngles = Vector3.zero;
    }

    public void UnEquipParachute()
    {
        if (!parachute)
        {
            return;
        }
        parachute.transform.parent = null;
        parachute.transform.eulerAngles = Vector3.zero;
        parachute.transform.Translate(new Vector3(0, 3, 0));
        parachute.collider2D.enabled = true;
        parachute.rigidbody2D.isKinematic = false;
        parachuteScript.enabled = true;
        this.parachuteScript = null;
        this.parachute = null;
        timeWithParachute = 0;
    }

    void Update()
    {
        if (!parachute)
        {
            return;
        }

        timeWithParachute += Time.deltaTime;
        if (timeWithParachute > 5)
        {
            parachute.transform.GetChild(0).gameObject.SetActive(true);
            transform.eulerAngles = Vector3.zero;
            isDeployed = true;
            Game.HasChute = true;
        }
    }
}
