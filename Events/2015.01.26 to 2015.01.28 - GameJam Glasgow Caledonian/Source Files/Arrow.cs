using UnityEngine;
using System.Collections;
using UnityEngine.UI;

public class Arrow : MonoBehaviour {
    public bool IsTop = false;
    public Transform Target;
    public GameObject TextUi;

    Text distanceText;
    Transform player;
    float distanceFromPlayer;

	void Start () {
        player = Game.PlayerBody.transform;
        distanceFromPlayer = transform.position.y - player.position.y;
        distanceText = TextUi.GetComponent<Text>();
	}

    void ToggleActive()
    {
        if (!Target)
        {
            return;
        }
        if (IsTop)
        {
            if (Target.position.y > Camera.main.ScreenToWorldPoint(new Vector3(0, Screen.height, 0)).y)
            {
                renderer.enabled = true;
                TextUi.SetActive(true);
            }
            else
            {
                renderer.enabled = false;
                TextUi.SetActive(false);
            }
        }
        else
        {
            if (Target.position.y < Camera.main.ScreenToWorldPoint(new Vector3(0, 0, 0)).y)
            {
                renderer.enabled = true;
                TextUi.SetActive(true);
            }
            else
            {
                renderer.enabled = false;
                TextUi.SetActive(false);
            }
        }
    }

    void UpdatePos()
    {
        if (!Target)
        {
            return;
        }

        Vector3 newPos = transform.position;
        newPos.x = Target.position.x;
        newPos.y = player.position.y + distanceFromPlayer;
        transform.position = newPos;

    }

	void Update () 
    {
        ToggleActive();
        UpdatePos();

        if (Target != null)
        {
            distanceText.text = Vector3.Distance(transform.position, Target.transform.position).ToString("#") + "m";
        }
	}
}
