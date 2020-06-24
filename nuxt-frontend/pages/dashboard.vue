<template>

  <v-container
    
    fluid
    grid-list-xl
    @load="onDashboardLoad"
  >
    <v-row>
      <v-select
          :items="firefighters_ex"
          filled
          label="Current FireFighter"
          v-model="selectedFirefighter"
          @input="changeFirefighter"

      ></v-select>
      <!-- firefighter - {{firefighter}} -->
    </v-row>

    <v-layout wrap>
      <v-flex
        sm6
        xs12
        md6
        lg3
      >
        <material-stats-card
          color="info"
          icon="mdi-chemical-weapon"
          title="CO"
          :value="firefighter.env.co"
          sub-icon="mdi-update"
          sub-text="15 seconds ago"
        />
      </v-flex>
      <v-flex
        sm6
        xs12
        md6
        lg3
      >
        <material-stats-card
          color="green"
          icon="mdi-temperature-celsius"
          title="Temperature"
          small-value="ºC"
          :value="firefighter.env.temp"
          sub-icon="mdi-update"
          sub-text="15 seconds ago"
        />
        
      </v-flex>
      <v-flex
        sm6
        xs12
        md6
        lg3
      >
        <material-stats-card
          color="red"
          icon="mdi-heart-pulse"
          title="Heart Rate"
          :value="firefighter.hr.hr"
          small-value="bpm"
          sub-icon="mdi-update"
          sub-text="15 seconds ago"
        />
      </v-flex>
      <v-flex
        sm6
        xs12
        md6
        lg3
      >
        <material-stats-card
          color="blue"
          icon="mdi-water-percent"
          title="NO2"
          :value="firefighter.env.no2"
          small-value="μg/mg³"
          sub-icon="mdi-update"
          sub-text="15 seconds ago"
        />
      </v-flex>

    </v-layout>
      <v-row>
        <material-chart-card
          :data="graph_co.data"
          :options="graph_co.options"
          color="info"
          type="Line"
        >
          <h4 class="title font-weight-light">{{firefighter.id}} CO Levels</h4>
          <p class="category d-inline-flex font-weight-light">
            Graph of temperature by time

          </p>

          <template slot="actions">
            <v-icon
              class="mr-2"
              small
            >
              mdi-clock-outline
            </v-icon>
            <span class="caption grey--text font-weight-light">updated 1 minute ago</span>
          </template>
        </material-chart-card>
      </v-row>
      <v-row>
        <material-chart-card
          :data="graph_hr.data"
          :options="graph_hr.options"
          color="red"
          type="Line"
        >
          <h4 class="title font-weight-light">{{firefighter.id}} Heart Rate BPM</h4>
          <p class="category d-inline-flex font-weight-light">Graph of Heart Rate BPM of firefighter</p>

          <template slot="actions">
            <v-icon
              class="mr-2"
              small
            >
              mdi-clock-outline
            </v-icon>
            <span class="caption grey--text font-weight-light">updated 1 minute ago</span>
          </template>
        </material-chart-card>
      </v-row>

    <v-row>
        <material-chart-card
          :data="graph_temperature.data"
          :options="graph_temperature.options"
          color="green"
          type="Line"
        >
          <h3 class="title font-weight-light">{{firefighter.id}} Temperature</h3>
          <p class="category d-inline-flex font-weight-light">Graph of temperature by time</p>

          <template slot="actions">
            <v-icon
              class="mr-2"
              small
            >
              mdi-clock-outline
            </v-icon>
            <span class="caption grey--text font-weight-light">updated 1 minute ago</span>
          </template>
        </material-chart-card>
      </v-row>
  </v-container>
</template>

<script>
  import axios from "axios"
  import materialCard from '~/components/material/AppCard'
  import materialChartCard from '~/components/material/AppChartCard'
  import materialStatsCard from '~/components/material/AppStatsCard'

  export default {
    layout: 'dashboard',
    middleware: 'authentication',
    components: {
      materialCard,
      materialChartCard,
      materialStatsCard
    },
    data() {
      return {
        graph_temperature: {
          data: {
            labels: [],
            series: [
              []
            ]
          },
          options: {
            
          low: 20,
          high: 45, // creative tim: we recommend you to set the high sa the biggest value + something for a better look
          chartPadding: {
            top: 0,
            right: 0,
            bottom: 0,
            left: 0
          }
          // maintainAspectRatio: true,
          }
        },

        graph_co: {
          data: {
            labels: [],
            series: [
              []
            ]
          },
          options: {
            
          low: 0,
          high: 300, // creative tim: we recommend you to set the high sa the biggest value + something for a better look
          chartPadding: {
            top: 0,
            right: 0,
            bottom: 0,
            left: 0
          }
          // maintainAspectRatio: true,
          }
        },
        graph_hr: {
          data: {
            labels: [],
            series: [
              []
            ]
          },
          options: {
            
          low: 40,
          high: 200, // creative tim: we recommend you to set the high sa the biggest value + something for a better look
          chartPadding: {
            top: 0,
            right: 0,
            bottom: 0,
            left: 0
          }
          // maintainAspectRatio: true,
          }
        },
        tabs: 0,
        list: {
          0: false,
          1: false,
          2: false
        },
        firefighters_ex: [
          'vr12', 'a1', 'a2'
        ],
        selectedFirefighter: 'a1',
        loading:false,
        firefighter:{ "id": "a1", "gps": { "id_temper": 51438, "date": "1557944700,0", "name": "vr12", "type": "gps", "gps_alt_tag": "1110", "gps_tag_lat": "40.06451668", "gps_tag_long": "-8.1615259", "gps_time_tag": "1110", "coordinates": [ "-8.1615259", "40.06451668" ] }, "env": { "id": 51444, "date": "1557944700,0", "name": "vr12", "type": "env", "co": "0", "temp": "28", "hgt": "-22", "no2": "0.9", "hum": "-1", "lum": "24", "battery": "101" }, "hr": { "id": 51493, "date": "1557944700,0", "name": "vr12", "type": "hr", "hr": "101.0217575" } }
        ,
        graph_temperature_timestamp:[],
        graph_co_series:[],
        graph_co_timestamp:[]
      }
    },
    methods: {
      complete (index) {
        this.list[index] = !this.list[index]
      },
      async onDashboardLoad(){
        const firefighters = await this.getFirefighters();
        const info = await this.getInfo();

        this.firefighters = firefighters;
        this.info = info;
        console.log('firefighters dashboard load', this.firefighters)
        console.log('info',this.info)

        for (var f in this.firefighters){
          // console.log(this.firefighters[f].id === 'vr12')
          // console.log(this.firefighters[f])
          if (this.firefighters[f].id === 'a1'){
            this.firefighter = this.firefighters[f]
            this.firefighter_graphs = this.info[f]
            }
          }     

          this.env = this.firefighter_graphs.env
          this.heartrate = this.firefighter_graphs.hr
          // console.log(this.env)
          // this.env.forEach( env_value => env_value.env = {'temp': env_value.temp, 'timestamp': env_value.date})
          //Popular os graficos
          var temp_series = []
          var temp_labels = []
          var co_series = []
          var co_labels = []
          var hr_series = []
          var hr_labels = []

          for (var e in this.env){
            var date = new Date(this.env[e].date * 1000)
            var string = date.getHours() + ':' + date.getMinutes()
            temp_series.push(this.env[e].temp)
            temp_labels.push(string)
            co_series.push(this.env[e].co)
            co_labels.push(string)
          }

          for (var hr in this.heartrate){
            var date = new Date(this.heartrate[hr].date * 1000)
            var string = date.getHours() + ':' + date.getMinutes()
            hr_series.push(this.heartrate[hr].hr)
            hr_labels.push(string)
      
          }

          this.graph_temperature.data.series[0] = temp_series
          this.graph_temperature.data.labels = temp_labels 
          this.graph_co.data.series[0] = co_series
          this.graph_co.data.labels = co_labels
          
          this.graph_hr.data.series[0] = hr_series
          this.graph_hr.data.labels = hr_labels

          console.log('heart rate graph ondashboardLoad',this.graph_hr)
          // console.log(this.graph_hr)
          
      
        
      },
      async getFirefighters(){
        // console.log($this.store.actions.get_firefighters)
        // const res = await this.$store.state.dispatch("get_firefighters")
        try{
          const res = await axios.get(this.getUrl() + '/fighters/all')
          return res.data
        }catch(error){
          console.log(error)
        }
      },
      async getInfo(){
        // console.log($this.store.actions.get_firefighters)
        // const res = await this.$store.state.dispatch("get_firefighters")
        try{
          const res = await axios.get(this.getUrl() + '/dashboard')

          return res.data
        }catch(error){
          console.log(error)
        }
      },
      async fetchAllInfo(){
        try{
          const res_dash = await axios.get(this.getUrl() + '/dashboard')
          const res_fighters = await axios.get(this.getUrl() + '/fighters/all')
          this.firefighters = res_fighters.data
          this.info = res_dash.data

          //Separa os bombeiros 
          this.firefighters.forEach( firefighter => firefighter.gps.coordinates = [firefighter.gps.gps_tag_long,firefighter.gps.gps_tag_lat])
          for (var f in this.firefighters){
            if (this.firefighters[f].id === this.selectedFirefighter){
              this.firefighter = this.firefighters[f]
              this.firefighter_graphs = this.info[f]
            }
          }

          //this.dataCompletedTasksChart.series = 
                    
          var temp_series = []
          var temp_labels = []
          var co_series = []
          var co_labels = []
          var hr_series = []
          var hr_labels = []

          for (var e in this.env){
            var date = new Date(this.env[e].date * 1000)
            var string = date.getHours() + ':' + date.getMinutes()
            temp_series.push(this.env[e].temp)
            temp_labels.push(string)
            co_series.push(this.env[e].co)
            co_labels.push(string)
          }

          for (var hr in this.heartrate){
            var date = new Date(this.heartrate[hr].date * 1000)
            var string = date.getHours() + ':' + date.getMinutes()
            hr_series.push(this.heartrate[hr].hr)
            hr_labels.push(string)
      
          }

          this.graph_temperature.data.series[0] = temp_series
          this.graph_temperature.data.labels = temp_labels 
          this.graph_co.data.series[0] = co_series
          this.graph_co.data.labels = co_labels
          this.graph_hr.data.series[0] = hr_series
          this.graph_hr.data.labels = hr_labels

          console.log('this.graph_hr fetchAllInfo',this.graph_hr)

        }catch(error){
          console.log(error)
        }   
      },
      changeFirefighter() {
        console.log('mudei estado')
        console.log(this.selectedFirefighter)
        // this.$emit('changeFirefighter', this.message)
        for (var f in this.firefighters){
            if (this.firefighters[f].id === this.selectedFirefighter){
              this.firefighter_graphs = this.info[f]
            }
          }

          this.env = this.firefighter_graphs.env
          this.heartrate = this.firefighter_graphs.hr


          var temp_series = []
          var temp_labels = []
          var co_series = []
          var co_labels = []
          var hr_series = []
          var hr_labels = []

          for (var e in this.env){
            var date = new Date(this.env[e].date * 1000)
            var string = date.getHours() + ':' + date.getMinutes()
            temp_series.push(this.env[e].temp)
            temp_labels.push(string)
            co_series.push(this.env[e].co)
            co_labels.push(string)
          }

          for (var hr in this.heartrate){
            var date = new Date(this.heartrate[hr].date * 1000)
            var string = date.getHours() + ':' + date.getMinutes()
            hr_series.push(this.heartrate[hr].hr)
            hr_labels.push(string)
      
          }

          this.graph_temperature.data.series[0] = temp_series
          this.graph_temperature.data.labels = temp_labels 
          this.graph_co.data.series[0] = co_series
          this.graph_co.data.labels = co_labels
          this.graph_hr.data.series[0] = hr_series
          this.graph_hr.data.labels = hr_labels

          console.log('this.graph_hr changeFirefighter',this.graph_hr)
          // }

          // this.dataCompletedTasksChart.series = this.graph_temperature_series

          // this.dataCompletedTasksChart.labels = this.graph_temperature_timestamp
        
          // console.log(this.dataCompletedTasksChart)

      },
      getUrl(){
        return "http://localhost:8080"
      }
    },
    mounted() {
      this.$nextTick(() => {
        // this.$nuxt.$loading.start()

        // setTimeout(() => this.$nuxt.$loading.finish(), 500)
        this.onDashboardLoad()
        console.log('onload firefighter', this.firefighter)

        /*this.dailySalesChart.options = {
          lineSmooth: this.$chartist.Interpolation.cardinal({
            tension: 0
          }),
          low: 0,
          high: 50, // creative tim: we recommend you to set the high sa the biggest value + something for a better look
          chartPadding: {
            top: 0,
            right: 0,
            bottom: 0,
            left: 0
          }
        };
        this.dataCompletedTasksChart.options = {
          lineSmooth: this.$chartist.Interpolation.cardinal({
            tension: 0
          }),
          low: 0,
          high: 1000, // creative tim: we recommend you to set the high sa the biggest value + something for a better look
          chartPadding: {
            top: 0,
            right: 0,
            bottom: 0,
            left: 0
          }
        };*/
      });
    },
    created(){
      console.log('entrei aqui')
      this.fetchAllInfo()
      this.timer = setInterval(this.fetchAllInfo, 10000)
    }
  }
</script>

<style scoped>
  /* .v-container {
    padding-top:10px;
  }
  .v-select {
    position: absolute;
    left: 10px;
    top: 0px;

  } */
</style>