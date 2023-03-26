import assetsObject from "../assets/assets";

export default function renderSessions({ data }) {
  let renderSessions = "";
  renderSessions = data.map((event) => (
    <View>
      <StudySessionCard
        tag={event.title}
        sessionTitle={"Studying Session"}
        sessionLocation={"Trottier"}
        numberOfAttendees={12}
        image={assetsObject.mcgillPhoto}
      />
    </View>
  ));

  return renderSessions;
}
