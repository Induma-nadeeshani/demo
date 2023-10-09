const onClick = async (_id) => {
    const result = await confirm("Are you sure you want to delete entry?", options);
    if (result) {
      console.log("You click yes!");
      setData(data.filter((item) => item._id !== _id));

      await axios.delete(`http://localhost:3002/doctor/delete/${_id}`);
      
      console.log(_id);
      return;

    }
    console.log("You click No!");
  };