export default function renderSessions({ data }) {
  let renderSessions = "";
  renderSessions = data.map((event) => (
    <View>
      <StudySessionCard
        tag={"ECSE-324"}
        sessionTitle={"Studying Session"}
        sessionLocation={"Trottier"}
        numberOfAttendees={12}
        image={assetsObject.mcgillPhoto}
      />
    </View>
  ));

  return renderSessions;
}
